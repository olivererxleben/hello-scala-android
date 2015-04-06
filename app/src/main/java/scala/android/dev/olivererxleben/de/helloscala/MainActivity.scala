package scala.android.dev.olivererxleben.de.helloscala

import android.app.Activity
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.ActionBarDrawerToggle
import android.support.v4.widget.DrawerLayout
import android.view.{MenuItem, Menu, View}
import android.widget.{ArrayAdapter, ListView, Button}


class MainActivity extends Activity with TypedActivity {

  private var menuTitles: Array[String] = null
  private var drawerLayout: DrawerLayout = null
  private var drawerList: ListView = null
  private var drawerToggle: ActionBarDrawerToggle = null
  private var drawerTitle: CharSequence = null
  private var title: CharSequence = null

  override def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    menuTitles = getResources.getStringArray(R.array.menuTitles)

    drawerLayout = findView(TR.dLayout)
    drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {

      override def onDrawerOpened(drawerView: View) = {
        super.onDrawerOpened(drawerView)
        getActionBar.setTitle(getTitle)
        invalidateOptionsMenu() // will call OnPrepareOptionMenu

      }

      def OnDrawerClosed(view: View) = {
        super.onDrawerClosed(view)

        getActionBar.setTitle(getTitle)
        invalidateOptionsMenu()
      }
    }

    drawerLayout.setDrawerListener(drawerToggle)

    getActionBar.setDisplayHomeAsUpEnabled(true)
    getActionBar.setHomeButtonEnabled(true)

    drawerList = findView(TR.dList)
    drawerList.setAdapter(new ArrayAdapter[String](this, R.layout.drawer_list_item, menuTitles))

  }

  override protected def onPostCreate(savedInstanceState: Bundle) = {
    super.onPostCreate(savedInstanceState)
    drawerToggle.syncState()
  }



  override protected def onPrepareOptionsMenu(menu: Menu): Boolean = {
    var drawerOpen: Boolean = drawerLayout.isDrawerOpen(drawerList)
    // TODO: if drawer is open hide Menu items
    //menu.findItem(R.id.)

    super.onPrepareOptionsMenu(menu)
  }

  override def onConfigurationChanged(config: Configuration) = {
    super.onConfigurationChanged(config)

    drawerToggle.onConfigurationChanged(config)
  }

  override def onOptionsItemSelected(item: MenuItem): Boolean = {
    if (drawerToggle.onOptionsItemSelected(item)) true

    // ...

    super.onOptionsItemSelected(item)

  }
}

object TR {

  val dLayout = TypedResource[DrawerLayout](R.id.drawer_layout)
  val dList = TypedResource[ListView](R.id.left_drawer)
}