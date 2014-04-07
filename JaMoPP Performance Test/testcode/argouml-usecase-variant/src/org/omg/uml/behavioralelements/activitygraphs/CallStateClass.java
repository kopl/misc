package org.omg.uml.behavioralelements.activitygraphs;

/**
 * CallState class proxy interface.
 *  
 * <p><em><strong>Note:</strong> This type should not be subclassed or implemented 
 * by clients. It is generated from a MOF metamodel and automatically implemented 
 * by MDR (see <a href="http://mdr.netbeans.org/">mdr.netbeans.org</a>).</em></p>
 */
public interface CallStateClass extends javax.jmi.reflect.RefClass {
    /**
     * The default factory operation used to create an instance object.
     * @return The created instance object.
     */
    public CallState createCallState();
    /**
     * Creates an instance object having attributes initialized by the passed 
     * values.
     * @param name 
     * @param visibility 
     * @param isSpecification 
     * @param isDynamic 
     * @param dynamicMultiplicity 
     * @param dynamicArguments 
     * @return The created instance object.
     */
    public CallState createCallState(java.lang.String name, org.omg.uml.foundation.datatypes.VisibilityKind visibility, boolean isSpecification, boolean isDynamic, org.omg.uml.foundation.datatypes.Multiplicity dynamicMultiplicity, org.omg.uml.foundation.datatypes.ArgListsExpression dynamicArguments);
}
