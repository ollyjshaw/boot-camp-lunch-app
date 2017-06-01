package service

import java.util.Calendar

import com.google.inject.ImplementedBy

class RealGreetingService extends GreetingService {
  def greeting: String = ???
}

@ImplementedBy(classOf[RealGreetingService])
trait GreetingService {
  def greeting: String
}