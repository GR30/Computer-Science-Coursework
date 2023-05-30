using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Player : MonoBehaviour
{
    public GameController gameController;
    public CharacterController characterController;

    ArrayList Inventory = new ArrayList();
    LayerMask interactableObject;

    public float moveSpeed_Base = 0.5f; // Normal Speed
    public float moveSpeed_Add = 0.5f; // Additional speed on run
    private Vector3 moveDirection;

    // Start is called before the first frame update
    void Start()
    {
        //gameController = GameObject.Find("Game Controller").GetComponent<GameController>();
        characterController = GetComponent<CharacterController>();

        transform.SetPositionAndRotation(transform.position, new Quaternion(0, 0, 0, 0));
    }

    // Update is called once per frame
    void Update()
    {
        // Run speed (Key Down), Normal Speed (Key Up)
        if (Input.GetKeyDown(KeyCode.Space)) { moveSpeed_Base += moveSpeed_Add; }
        if (Input.GetKeyUp(KeyCode.Space)) { moveSpeed_Base -= moveSpeed_Add; }

        moveDirection = (transform.forward * Input.GetAxis("Vertical") * moveSpeed_Base) + (transform.right * Input.GetAxis("Horizontal") * moveSpeed_Base);
        moveDirection = moveDirection.normalized * moveSpeed_Base;
        characterController.Move(moveDirection);

        if (Input.GetKeyDown(KeyCode.Mouse0)) { Interaction(); }
    }

   


    void Interaction()
    {
        Collider[] interactableObjects = Physics.OverlapSphere(transform.position, 3f, interactableObject, QueryTriggerInteraction.Ignore);

        // Find nearest object to be interacted with.
        Collider nearestInterObj = null;
        foreach (Collider obj in interactableObjects)
        {
            if (nearestInterObj = null) { nearestInterObj = obj; }
            else
            {
                if (Vector3.Distance(obj.transform.position, transform.position) < Vector3.Distance(nearestInterObj.transform.position, transform.position))
                {
                    nearestInterObj = obj;
                }
            }
        }

        /*
         * Add code for object interaction from the object(s) side.
         *  1. Account for diffreing type of object being interacted with
         *    - Door
         *    - Pickup
         *    - etc.
         */

        /*
        if (nearestInterObj.gameObject.GetComponentInChildren<DoorScript>() != null)
        {

        }
        else
        {
            Inventory.Add(nearestInterObj.gameObject);
        }
        */
    }

    
}

