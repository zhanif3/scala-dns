/**
 * *****************************************************************************
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
 * ****************************************************************************
 */
package records
import org.jboss.netty.buffer.ChannelBuffer
import payload.Name
import org.slf4j.LoggerFactory

case class PTR(record: List[Array[Byte]]) extends AbstractRecord {

  val description = "PTR"
  
  def toByteArray = Name.toByteArray(record)
  
  def isEqualTo(any: Any) = any match {
    case r: PTR => r.record.toArray.deep == record.toArray.deep
    case _ => false
  }
  
  def toCompressedByteArray(input: (Array[Byte], Map[String, Int])) = Name.toCompressedByteArray(record, input)
}

object PTR {

  val logger = LoggerFactory.getLogger("app")

  def apply(buf: ChannelBuffer, recordclass: Int, size: Int, offset: Int = 0) = {
    val record = recordclass match {
      // IN
      case 1 => Name.parse(buf, offset)
      // *
      case 255 => null // not implemented yet
      case _ => throw new Error("Unknown record class")
    }
    new PTR(record)
  }
}
