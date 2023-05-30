using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SecurityCamera : MonoBehaviour
{
    public GameController gameController;

    public bool rotateLeft = false;
    public float rotationAmount = 0; // 0 for no rotation
    public float rotateSpeed = 1;

    private float rotateY = 1;
    private float rotationCap;
    private Vector3 rotationStart;
    private Vector3 rotationEnd;
    private Vector3 rotationTarget;
    private bool turnBack;

    private Space spacePivot = Space.Self;

    // Start is called before the first frame update
    void Start()
    {
        
        if(rotateLeft == true)
        {
            rotationAmount = 0 - rotationAmount;
        }

        //rotationCap = transform.rotation.y + rotationAmount;
        
        rotateY = 1;
        /*
        rotationStart.x = transform.rotation.x;
        rotationStart.y = transform.rotation.y;
        rotationStart.z = transform.rotation.z;

        rotationEnd.x = transform.rotation.x;
        rotationEnd.y = rotationCap;
        rotationEnd.z = transform.rotation.z;

        rotationTarget = rotationEnd;
        */
        turnBack = false;
    }

    // Update is called once per frame
    void FixedUpdate()
    {
        //transform.Rotate(0, rotateSpeed * rotateY, 0, Space.Self);
        /*
        if(rotationTarget == rotationEnd)
        {
            rotationTarget = rotationStart;
        }
        if(rotationTarget == rotationStart)
        {
            rotationTarget = rotationEnd;
        }*/

    }

    private void OnTriggerEnter(Collider coll)
    {
        Debug.Log("Hit me");
        if (coll.tag == "Player")
        {
            gameController.Player.gameObject.SetActive(false);
            gameController.toCheckPoint();

        }
    }
}
