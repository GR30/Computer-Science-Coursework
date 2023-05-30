using System.Collections;
using System.Collections.Generic;
using UnityEngine;


public class Door : MonoBehaviour
{
    private int correctPin = 2193;
    private bool open = false;
    private void OnTriggerEnter(Collider other)
    {
        if(other.CompareTag("Player"))
        {
            
        }
    }
}
   


