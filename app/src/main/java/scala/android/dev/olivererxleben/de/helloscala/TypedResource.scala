package scala.android.dev.olivererxleben.de.helloscala

import android.app.Activity
import android.view.View

case class TypedResource[T](id: Int)

object TypedResource {
  implicit def view2typed(v: View) = new TypedViewHolder { def view = v }
  implicit def activity2typed(act: Activity) = new TypedActivityHolder { def activity = act }
}
