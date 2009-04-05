package com.liftworkshop.snippet 
 
import com.liftworkshop._ 
import model._ 
 
import net.liftweb._ 
import http._ 
import SHtml._ 
import S._ 
 
import js._ 
import JsCmds._ 
 
import mapper._ 
 
import util._ 
import Helpers._ 
 
import scala.xml.{NodeSeq, Text} 
 
class TD { 
 def add(form: NodeSeq) = { 
   val todo = ToDo.create.owner(User.currentUser) 
 
   def checkAndSave(): Unit = 
   todo.validate match { 
    case Nil => todo.save ; S.notice(~Added ~+todo.desc) 
    case xs => S.error(xs) ; S.mapSnippet(~TD.add~, doBind) 
   } 
 
   def doBind(form: NodeSeq) = 
   bind(~todo~, form, 
      ~priority~ -> todo.priority.toForm, 
      ~desc~ -> todo.desc.toForm, 
      ~submit~ -> submit(~New~, checkAndSave)) 
 
   doBind(form) 
 } 
}
