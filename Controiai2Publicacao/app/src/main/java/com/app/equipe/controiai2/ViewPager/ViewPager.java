package com.app.equipe.controiai2.ViewPager;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.app.equipe.controiai2.Entrar;
import com.app.equipe.controiai2.ProfissionalCategorias3;
import com.app.equipe.controiai2.ProfissionalPerfil;
import com.app.equipe.controiai2.R;

import com.app.equipe.controiai2.domain.Profissional;
import com.app.equipe.controiai2.util.LibraryClass;
import com.firebase.client.Firebase;
import com.pixelcan.inkpageindicator.InkPageIndicator;

public class ViewPager extends AppCompatActivity {
    InkPageIndicator inkPageIndicator;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    Profissional profissionalu;
    private Firebase firebase;


    private android.support.v4.view.ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        inkPageIndicator = (InkPageIndicator) findViewById(R.id.indicator);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());




        // Set up the ViewPager with the sections adapter.
        mViewPager = (android.support.v4.view.ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        firebase = LibraryClass.getFirebase();
        profissionalu = new Profissional();

        verifyUserLogged();

        /*try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getPackageName(),
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        }
        catch (PackageManager.NameNotFoundException e) {

        }
        catch (NoSuchAlgorithmException e) {

        }*/
        inkPageIndicator.setViewPager(mViewPager);

    }

    private void verifyUserLogged(){
        if(firebase.getAuth() != null){
            Intent intent;
            profissionalu.retrieveIdSP(this);
            String tipo = profissionalu.retrieveTipoSP(this);
            switch (tipo){
                case "Anonimo":

                    break;
                case "Profissional":
                    intent = new Intent(getBaseContext(),ProfissionalPerfil.class);
                    startActivity(intent);
                    break;
                default:


            }
        }

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_view_pager, container, false);

            ScrollView relative = (ScrollView) rootView.findViewById(R.id.scroll1);
            ScrollView relative2 = (ScrollView) rootView.findViewById(R.id.scroll2);
            ScrollView relative3 = (ScrollView) rootView.findViewById(R.id.scroll3);

            switch (getArguments().getInt(ARG_SECTION_NUMBER)){
                case 1:
                    relative.setVisibility(View.VISIBLE);
                    relative2.setVisibility(View.GONE);
                    break;
                case 2:
                    relative2.setVisibility(View.VISIBLE);
                    relative.setVisibility(View.GONE);
                    break;
                case 3:
                    relative3.setVisibility(View.VISIBLE);
                    relative2.setVisibility(View.GONE);
                    break;

            }

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }

    public void encontrar(View view){
        Intent intent = new Intent(this,ProfissionalCategorias3.class);
        startActivity(intent);
    }

    public void comecar(View view){
        Intent intent = new Intent(this,Entrar.class);
        startActivity(intent);
    }
    public void cadastro(View view){


    }

}
