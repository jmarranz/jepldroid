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

import jepl.JEPLTask;

/**
 *
 * @author jmarranz
 */
public class JEPLTaskOneExecWithConnectionWrapperImpl<T> extends JEPLTaskOneExecWithConnectionImpl<T>
{
    protected JEPLTask<T> task;

    public JEPLTaskOneExecWithConnectionWrapperImpl(JEPLTask<T> task)
    {
        this.task = task;
    }

    @Override
    public JEPLTask<T> getInnerJEPLTask()
    {
        return getInnerJEPLTask(task);
    }

    @Override
    protected T execInherit() throws Exception
    {
        return task.exec();
    }

    public static <T> JEPLTask<T> getInnerJEPLTask(JEPLTask<T> task)
    {
        if (task instanceof JEPLTaskOneExecWithConnectionWrapperImpl)
            return ((JEPLTaskOneExecWithConnectionWrapperImpl<T>)task).getInnerJEPLTask();
        else
            return task;
    }
        
}
