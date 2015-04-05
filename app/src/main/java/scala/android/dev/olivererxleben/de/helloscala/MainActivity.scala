package scala.android.dev.olivererxleben.de.helloscala

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.{LinearLayoutManager, RecyclerView}
import android.view.View
import android.widget.Button

class MainActivity extends Activity with TypedActivity {


  override def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
//    val textView = findViewById(R.id.hello_world).asInstanceOf[TextView]
//    textView.setText("Hello Scalalalalala")

    val button = findView(TR.button)
  }
}

case class TypedResource[T](id: Int)

object TR {

  val button = TypedResource[Button](R.id.button)
}

trait TypedViewHolder {
  def view: View
  def findView[T](tr: TypedResource[T]) = view.findViewById(tr.id).asInstanceOf[T]
}

trait TypedView extends View with TypedViewHolder { def view = this }

trait TypedActivityHolder {
  def activity: Activity
  def findView[T](tr: TypedResource[T]) = activity.findViewById(tr.id).asInstanceOf[T]
}

trait TypedActivity extends Activity with TypedActivityHolder { def activity = this }

object TypedResource {
  implicit def view2typed(v: View) = new TypedViewHolder { def view = v }
  implicit def activity2typed(act: Activity) = new TypedActivityHolder { def activity = act }
}