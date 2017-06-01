package service

import com.google.inject.ImplementedBy

class RealGreetingService extends GreetingService {
  def greeting: String = "Alright!"
}

@ImplementedBy(classOf[RealGreetingService])
trait GreetingService {
  def greeting: String
}