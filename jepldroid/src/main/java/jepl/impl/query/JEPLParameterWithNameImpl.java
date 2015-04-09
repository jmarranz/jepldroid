/*
   Copyright 2011 Jose Maria Arranz Santamaria

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

package jepl.impl.query;

/**
 *
 * @author jmarranz
 */
public class JEPLParameterWithNameImpl<T> extends JEPLParameterImpl<T>
{
    public JEPLParameterWithNameImpl(JEPLParameterDecWithNameImpl dec,JEPLParameterValueNamedImpl<T> paramValue)
    {
        super(dec);
        this.paramValue = paramValue;
        paramValue.setJEPLParameterWithName(this);
    }

    public JEPLParameterValueNamedImpl<T> getJEPLParameterValueNamed()
    {
        return (JEPLParameterValueNamedImpl<T>)paramValue;
    }

    @Override
    public String getErrorMessageValueNotDefined()
    {
        return "Parameter is not defined on parameter with name " + getName();
    }
}