package com.example.practica01v22

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practica01v22.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //Vincular las vistas con MainActivity
    private lateinit var binding: ActivityMainBinding

    // ArrayList of class ItemsViewModel
    val data = ArrayList<Alumno>()

    // This will pass the ArrayList to our Adapter
    val adapter = AlumnoAdapter(this,data)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)



        //Agregar elementos a la lista
            data.add(Alumno("Juan","20134567","juan@gmail.com","https://i.pinimg.com/236x/c0/f6/4e/c0f64ec392b0fe28c269ae92e003c1ba.jpg"))
            data.add(Alumno("Paulo","20156638","prosas1@ucol.com","https://i.pinimg.com/236x/a2/6f/ec/a26fec0961a1b32f56b861930c528256.jpg"))
            data.add(Alumno("Celia","20198765","celia@gmail.com","https://i.pinimg.com/236x/96/2b/50/962b50453f63ea3ef8c0e281a300c279.jpg"))
            data.add(Alumno("Franco","20167890","k_franco@hotmail.com","https://i.pinimg.com/236x/7d/53/01/7d53013d63fc2b06e91e09d72f60e8b4.jpg"))




        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

        adapter.setOnItemClickListener(object: AlumnoAdapter.ClickListener{
            override fun onItemClick(view: View, position: Int) {
                //Toast.makeText(this@MainActivity,"Click en ${position}",Toast.LENGTH_LONG).show()
                itemOptiomsMenu(position)
            }
        })
        //Variable para recibir extras
        val parExtras = intent.extras
        val msje = parExtras?.getString("mensaje")
        val nombre = parExtras?.getString("nombre")
        val cuenta = parExtras?.getString("cuenta")
        val correo = parExtras?.getString("correo")
        val image = parExtras?.getString("image")
        if (msje == "nuevo"){
            val insertIndex: Int = data.count()
            data.add(insertIndex,
                Alumno(
                    "${nombre}",
                    "${cuenta}",
                    "${correo}",
                    "${image}"
                )
            )
            adapter.notifyItemInserted(insertIndex)
        }

        //Click en agregar
        binding.faButton.setOnClickListener {
            val intento1 = Intent(this,MainActivityNuevo::class.java)
            //intento1.putExtra("valor1","Hola Mundo")
            startActivity(intento1)
        }
    }





    private fun itemOptiomsMenu(position: Int) {
        val popupMenu = PopupMenu(this,binding.recyclerview[position].findViewById(R.id.textViewOptions))
        popupMenu.inflate(R.menu.option_menu)
//Para cambiarnos de activity
        val intento2 = Intent(this,MainActivityNuevo::class.java)
//Implementar el click en el item
        popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener{
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                when(item?.itemId){
                    R.id.borrar -> {
                        val tmpAlum = data[position]
                        data.remove(tmpAlum)
                        adapter.notifyDataSetChanged()
                        return true
                    }
                    R.id.editar ->{
                        //Tomamos los datos del alumno, en la posición de la lista donde hicieron click
                        val nombre = data[position].nombre
                        val cuenta = data[position].cuenta
                        val correo = data[position].correo
                        val image = data[position].imagen
                        //En position tengo el indice del elemento en la lista
                        val idAlum: Int = position
                        intento2.putExtra("mensaje","edit")
                        intento2.putExtra("nombre","${nombre}")
                        intento2.putExtra("cuenta","${cuenta}")
                        intento2.putExtra("correo","${correo}")
                        intento2.putExtra("image","${image}")
                        //Pasamos por extras el idAlum para poder saber cual editar de la lista (ArrayList)
                        intento2.putExtra("idA",idAlum)
                        startActivity(intento2)
                        return true
                    }
                }
                return false
            }
        })
        popupMenu.show()
    }
}