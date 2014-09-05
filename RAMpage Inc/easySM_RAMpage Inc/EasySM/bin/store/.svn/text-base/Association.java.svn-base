/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package store;

import datatypes.AssociationInfo;
import store.Class;
/**
 *
 * @author davide
 */
public class Association extends CDElement{

    Class source;
    Class dest;
    String sourceName;
    String destName;

    public Association (AssociationInfo a){
        setName(a.getName());
        source=a.getSource();
        dest=a.getDest();
        sourceName=a.getSourceName();
        destName=a.getDestName();

    }

    public void setDest(Class dest) {
        this.dest = dest;
    }

    public void setDestName(String destName) {
        this.destName = destName;
    }

    public void setSource(Class source) {
        this.source = source;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public Class getDest() {
        return dest;
    }

    public String getDestName() {
        return destName;
    }

    public Class getSource() {
        return source;
    }

    public String getSourceName() {
        return sourceName;
    }
}
