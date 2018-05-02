/*
 * Copyright 2010-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.script

import org.jetbrains.kotlin.descriptors.ScriptDescriptor
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.types.KotlinType
import java.util.*

data class ScriptParameter(val name: Name, val type: KotlinType)

interface ScriptHelper {
    fun getScriptParameters(kotlinScriptDefinition: KotlinScriptDefinition, scriptDefinition: ScriptDescriptor): List<ScriptParameter>
    fun getScriptSuperType(scriptDescriptor: ScriptDescriptor, scriptDefinition: KotlinScriptDefinition): KotlinType

    companion object {
        private val scriptHelperInstance: ScriptHelper =
            ServiceLoader.load(ScriptHelper::class.java, ScriptHelper::class.java.classLoader).firstOrNull()
                    ?: error("ScriptHelper implementation is not found")

        fun getInstance(): ScriptHelper = scriptHelperInstance
    }
}
