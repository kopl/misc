package org.omg.uml.foundation.core;

/**
 * A_modelElement_templateArgument association proxy interface.
 *  
 * <p><em><strong>Note:</strong> This type should not be subclassed or implemented 
 * by clients. It is generated from a MOF metamodel and automatically implemented 
 * by MDR (see <a href="http://mdr.netbeans.org/">mdr.netbeans.org</a>).</em></p>
 */
public interface AModelElementTemplateArgument extends javax.jmi.reflect.RefAssociation {
    /**
     * Queries whether a link currently exists between a given pair of instance 
     * objects in the associations link set.
     * @param modelElement Value of the first association end.
     * @param templateArgument Value of the second association end.
     * @return Returns true if the queried link exists.
     */
    public boolean exists(org.omg.uml.foundation.core.ModelElement modelElement, org.omg.uml.foundation.core.TemplateArgument templateArgument);
    /**
     * Queries the instance object that is related to a particular instance object 
     * by a link in the current associations link set.
     * @param templateArgument Required value of the second association end.
     * @return Related object or <code>null</code> if none exists.
     */
    public org.omg.uml.foundation.core.ModelElement getModelElement(org.omg.uml.foundation.core.TemplateArgument templateArgument);
    /**
     * Queries the instance objects that are related to a particular instance 
     * object by a link in the current associations link set.
     * @param modelElement Required value of the first association end.
     * @return Collection of related objects.
     */
    public java.util.Collection getTemplateArgument(org.omg.uml.foundation.core.ModelElement modelElement);
    /**
     * Creates a link between the pair of instance objects in the associations 
     * link set.
     * @param modelElement Value of the first association end.
     * @param templateArgument Value of the second association end.
     */
    public boolean add(org.omg.uml.foundation.core.ModelElement modelElement, org.omg.uml.foundation.core.TemplateArgument templateArgument);
    /**
     * Removes a link between a pair of instance objects in the current associations 
     * link set.
     * @param modelElement Value of the first association end.
     * @param templateArgument Value of the second association end.
     */
    public boolean remove(org.omg.uml.foundation.core.ModelElement modelElement, org.omg.uml.foundation.core.TemplateArgument templateArgument);
}
