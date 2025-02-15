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
package payload
import org.jboss.netty.buffer.ChannelBuffer
import org.slf4j.LoggerFactory

case class Question(qname: List[Array[Byte]], qtype: Int, qclass: Int) {
  def toByteArray = 
    Name.toByteArray(qname) ++ RRData.shortToBytes(qtype.toShort) ++ RRData.shortToBytes(qclass.toShort)
    
  def toCompressedByteArray(input: (Array[Byte], Map[String, Int])) = {
    val qnameBytes = Name.toCompressedByteArray(qname, input)
    (qnameBytes._1 ++ RRData.shortToBytes(qtype.toShort) ++ RRData.shortToBytes(qclass.toShort), qnameBytes._2)
  }
    
}
  
  
  
  
  
  
  //  QNAME         a domain name represented as a sequence of labels, where
  //                each label consists of a length octet followed by that
  //                number of octets.  The domain name terminates with the
  //                zero length octet for the null label of the root.  Note
  //                that this field may be an odd number of octets; no
  //                padding is used.
  //var qname = ""
    
  // QTYPE        a two octet code which specifies the type of the query.
  //              The values for this field include all codes valid for a
  //              TYPE field, together with some more general codes which
  //              can match more than one type of RR.  
  //var qtype = ""
    
  // QCLASS       a two octet code that specifies the class of the query.
  //              For example, the QCLASS field is IN for the Internet.
  //var qclass = ""


object Question {
  val logger = LoggerFactory.getLogger("app")
  
  def apply(buf: ChannelBuffer, offset: Int) = 
    new Question(
      Name.parse(buf, offset), 
      buf.readUnsignedShort,
      buf.readUnsignedShort
    )
}