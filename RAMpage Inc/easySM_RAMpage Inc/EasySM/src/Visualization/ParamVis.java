/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Visualization;

import store.Parameter;


public class ParamVis{
        String type;
        String name;
        Parameter linkedParam;

        public ParamVis(Parameter p){
            type=p.getType().getName();
            name=p.getName();
            linkedParam=p;
        }

        public String getType(){
            return type;
        }
        public String getName(){
            return name;
        }
        public String getFullString(){
            return name + ": " + type;
        }

        public Parameter getLinkedParam(){
            return linkedParam;
        }
    }