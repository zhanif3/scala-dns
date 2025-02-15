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
package enums

object RecordType extends Enumeration {

  val A = Value(1)
  val NS = Value(2)
  val CNAME = Value(5)
  val SOA = Value(6)
  val PTR = Value(12)
  val MX = Value(15)
  val TXT = Value(16)
  val AAAA = Value(28)
  val SRV = Value(33)
  val OPT = Value(41)
  
  val AXFR = Value(252)
  val ALL = Value(255)
  
}
