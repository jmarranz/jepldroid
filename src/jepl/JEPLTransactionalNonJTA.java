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

package jepl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to describe the JDBC auto-commit mode (transaction mode) in {@link JEPLTask#exec()}
 * method executed by a non-JTA capable data source.
 *
 * @author jmarranz
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD}) 
public @interface JEPLTransactionalNonJTA
{
    /**
     * Specifies the required auto-commit mode.
     *
     * @return the auto-commit mode.
     */
    boolean autoCommit() default false;
}
