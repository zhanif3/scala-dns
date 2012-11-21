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
import org.slf4j.LoggerFactory

class A(buf: ChannelBuffer, recordclass: Int, size: Int) extends AbstractRecord(buf,recordclass,size){
  
  val logger = LoggerFactory.getLogger("app")
  
  val description = "A"
	val record = recordclass match {
    	// IN
    	case 1 => buf.readUnsignedInt() //return a 32 bit Internet Address
    	// *
    	case 255 => null// not implemented yet
    }
	
	logger.info(record.toString)
  
}
