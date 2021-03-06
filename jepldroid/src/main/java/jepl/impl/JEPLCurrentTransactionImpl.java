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
package jepl.impl;

import jepl.JEPLTransaction;

/**
 *
 * @author jmarranz
 */
public abstract class JEPLCurrentTransactionImpl implements JEPLTransaction
{
    protected JEPLConnectionImpl conWrap;
    protected boolean used;
    protected JEPLUserDataMonoThreadImpl userData = new JEPLUserDataMonoThreadImpl();

    public JEPLCurrentTransactionImpl(JEPLConnectionImpl conWrap)
    {
        this.conWrap = conWrap;
    }

    public String[] getUserDataNames()
    {
        return userData.getUserDataNames();
    }

    public boolean containsName(String name)
    {
        return userData.containsName(name);
    }

    public Object getUserData(String name)
    {
        return userData.getUserData(name);
    }

    public <T> T getUserData(String name, Class<T> returnType)
    {
        return userData.getUserData(name, returnType);
    }

    public Object setUserData(String name, Object value)
    {
        return userData.setUserData(name, value);
    }

    public Object removeUserData(String name)
    {
        return userData.removeUserData(name);
    }

    public <T> T removeUserData(String name, Class<T> returnType)
    {
        return userData.removeUserData(name, returnType);
    }
    
    public boolean isUsed()
    {
        return used;
    }

    public void begin()
    {
        this.used = true;
    }

    public void commit()
    {
        this.used = true;
    }

    public void rollback()
    {
        this.used = true;
    }
}
