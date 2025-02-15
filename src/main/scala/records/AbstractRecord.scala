/*******************************************************************************
 * Copyright 2012 silenteh
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package records
import org.jboss.netty.buffer.ChannelBuffer

abstract class AbstractRecord {
  
  val description: String
  
  def toByteArray: Array[Byte]
  
  def toCompressedByteArray(input: (Array[Byte], Map[String, Int])): (Array[Byte], Map[String, Int])
  
  def isEqualTo(any: Any): Boolean
}

object AbstractRecord {
  lazy val MAX_STRING_LENGTH = 255
}