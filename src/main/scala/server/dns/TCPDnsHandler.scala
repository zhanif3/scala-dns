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
package server.dns
import org.jboss.netty.channel.SimpleChannelUpstreamHandler
import org.jboss.netty.channel.ChannelHandlerContext
import org.jboss.netty.channel.MessageEvent
import org.slf4j.LoggerFactory
import payload.Message
import payload.RRData
import records._
import org.jboss.netty.buffer.ChannelBuffers
import org.jboss.netty.channel.ChannelFutureListener
import scala.Array.canBuildFrom

class TCPDnsHandler extends SimpleChannelUpstreamHandler {

  val logger = LoggerFactory.getLogger("app")

  override def messageReceived(ctx: ChannelHandlerContext, e: MessageEvent) {
    logger.info("This request is brought to you by TCP")
    val sourceIP = e.getRemoteAddress.toString
    e.getMessage match {
      case message: Message => {
        val responses = DnsResponseBuilder(message, sourceIP)

        if(responses.length == 1) {
          logger.debug("Compressed response length: " + responses.head.length.toString)
          logger.debug("Compressed response bytes: " + responses.head.toList.toString)
        }
        
        Array.tabulate(responses.length) {i =>
          val response = responses(i)
          if(i < responses.length - 1) {
            e.getChannel.write(ChannelBuffers.copiedBuffer(RRData.shortToBytes(response.length.toShort) ++ response))
          } else {
            e.getChannel.write(ChannelBuffers.copiedBuffer(RRData.shortToBytes(response.length.toShort) ++ response))
          .addListener(ChannelFutureListener.CLOSE)
          }
        }
      }
      case _ => {
        logger.error("Unsupported message type")
      }
    }
  }
}
