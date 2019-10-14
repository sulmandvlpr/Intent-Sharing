

package com.example.android.navigation

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.core.app.ShareCompat
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.android.navigation.databinding.FragmentGameWonBinding
import android.content.pm.ResolveInfo
import android.content.pm.PackageManager
import android.view.View.Y


class GameWonFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_game_won, container, false)
        binding.nextMatchButton.setOnClickListener { view: View ->
            view.findNavController().navigate(
                    GameWonFragmentDirections.actionGameWonFragmentToGameFragment())
        }


        // TODO (01) Add setHasOptionsMenu(true)
        setHasOptionsMenu(true)
        // This allows onCreateOptionsMenu to be called
        return binding.root
    }


    // TODO (02) Create getShareIntent method
    //Create shareIntent
private fun getShareIntent() :Intent{
        var args = GameWonFragmentArgs.fromBundle(arguments!!) //get Args form bundle

return ShareCompat.IntentBuilder.from(activity).setText(getString(R.string.share_success_text,args.numCorrect,args.numQuestions))
        .setType("text/plain")
        .intent
    }
    // TODO (03) Create shareSuccess method

private fun shareSucess(){
startActivity(getShareIntent())
}
    // TODO (04) Override and fill out onCreateOptionsMenu
    // Inflate the winner_menu and set the share menu item to invisible if the activity doesn't
    // resolve
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
    inflater?.inflate(R.menu.winner_menu,menu)

    //check if activity resolves
        if (null == getShareIntent().resolveActivity(activity!!.packageManager)){
            //hide menu if it doesn't resolve
            menu?.findItem(R.id.share)?.setVisible(false)

        }

    }
    // TODO (05) Override onOptionsItemSelected
    // Call the shareSuccess method when the item id matches R.id.share
   //Hook menu  action to menu
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.share -> shareSucess()
        }
        return super.onOptionsItemSelected(item)

    }
}

