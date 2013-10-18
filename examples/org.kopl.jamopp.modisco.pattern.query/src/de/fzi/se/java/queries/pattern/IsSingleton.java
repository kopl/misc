package de.fzi.se.java.queries.pattern;

import org.eclipse.gmt.modisco.infra.query.core.exception.ModelQueryExecutionException;
import org.eclipse.gmt.modisco.infra.query.core.java.IJavaModelQuery;
import org.eclipse.gmt.modisco.infra.query.core.java.ParameterValueList;
import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.members.Constructor;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.members.Method;
import org.emftext.language.java.modifiers.Modifier;
import org.emftext.language.java.modifiers.Private;
import org.emftext.language.java.modifiers.Protected;
import org.emftext.language.java.modifiers.Public;
import org.emftext.language.java.modifiers.Static;
import org.emftext.language.java.types.Type;

/**
 * MoDisco Model Query to identify singleton patterns in a JaMoPP java model.
 * Adapted from query in Eclipse Magazin 6.13
 * Article: "Tanzkurs unter der Kugel" 
 * Author: Lars Martin
 */
public class IsSingleton implements IJavaModelQuery<org.emftext.language.java.classifiers.Class, Boolean> {

	@Override
	public Boolean evaluate(org.emftext.language.java.classifiers.Class context, ParameterValueList parameterValues)
			throws ModelQueryExecutionException {
		
		boolean isSingleton = false;
		if(hasStaticField(context)){
			if(hasStaticGetter(context)){
				if(hasNoPublicConstructors(context)){
					isSingleton = true;
				}
			}
		}
		
		return isSingleton;
	}

	private boolean hasNoPublicConstructors(Class context) {
		boolean privateConstructor = false;
		boolean publicConstructor = false;
		
		for(Member member : context.getMembers()){
			if(member instanceof Constructor){
				Constructor constructor = (Constructor) member;
				for(Modifier modifier : constructor.getModifiers()){
					if(modifier instanceof Public){
						publicConstructor = true;
					}
					if(modifier instanceof Private){
						privateConstructor = true;
					}
					if(modifier instanceof Protected){
						privateConstructor = true;
					}
				}
			}
		}
		
		return privateConstructor && !publicConstructor;
	}

	private boolean hasStaticGetter(Class context) {
		for(Method method : context.getMethods()){
			for(Modifier modifier : method.getModifiers()){
				if(modifier instanceof Static){
					Type returnType = method.getTypeReference().getTarget();
					if(returnType instanceof Classifier){
						return typeInheritsFrom((Classifier) returnType, context);
					}
				}
			}
		}
		return false;
	}

	private boolean hasStaticField(Class context) {
		
		for(Field field : context.getFields()){
			for(Modifier modifier : field.getModifiers()){
				if(modifier instanceof Static){
					Type fieldType = field.getTypeReference().getTarget();
					if(fieldType instanceof Classifier){
						return typeInheritsFrom((Classifier) fieldType, context);
					}
				}
			}
		};
		
		return false;
	}
	
	private boolean typeInheritsFrom(Classifier fieldClassifier, Class context) {
		
		Classifier superClass = context;
		while(superClass != null 
				&& superClass instanceof Class 
				&& !classifierAreEqual(fieldClassifier,superClass) 
				&& isNotObjectClass((Class) superClass)){
			
			if(((Class) superClass).getExtends() != null){
				superClass = (Classifier) ((Class) superClass).getExtends().getTarget();
			} else {
				superClass = null;
			}
		}
		
		return (superClass != null && classifierAreEqual(fieldClassifier,superClass));
	}

	private boolean classifierAreEqual(Classifier classifier1, Classifier classifier2){
		String fqnClassifier1 = getFullQualifiedName(classifier1);
		String fqnClassifier2 = getFullQualifiedName(classifier2);
		return fqnClassifier1.equals(fqnClassifier2);
	}
	
	private boolean isNotObjectClass(Class classDecl) {
		String fullQualifiedName = getFullQualifiedName(classDecl);
		return !("java.lang.Object".equals(fullQualifiedName));
	}

	private String getFullQualifiedName(Classifier classifier) {
		String fullQualifiedName = null;
		StringBuilder fullQualifiedNameBuilder = new StringBuilder();
		for(String packagePart : classifier.getContainingPackageName()){
			fullQualifiedNameBuilder.append(packagePart);
			fullQualifiedNameBuilder.append(".");
		}
		fullQualifiedNameBuilder.append(classifier.getName());
		fullQualifiedName = fullQualifiedNameBuilder.toString();
		return fullQualifiedName;
	}
}
