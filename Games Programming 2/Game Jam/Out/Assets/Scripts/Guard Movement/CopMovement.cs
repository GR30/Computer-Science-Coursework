using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CopMovement : MonoBehaviour
{
    public GameController gameController;

    public GameObject path;
    public Path followPath;
    
    public int CurrentWayPointID = 0;
    public float speed;
    private float reachDistance = 1.0f;
    private float rotationSpeed = 5.0f;

    Vector3 last_position;
    Vector3 current_position;

    //initializing the path
    void Start()
    {
        followPath = path.GetComponent<Path>();
        last_position = transform.position;
    }

    private void FixedUpdate()
    {
        float distance = Vector3.Distance(followPath.path_objs[CurrentWayPointID].position, transform.position);
        transform.position = Vector3.MoveTowards(transform.position, followPath.path_objs[CurrentWayPointID].position, Time.deltaTime * speed);

        var rotation = Quaternion.LookRotation(followPath.path_objs[CurrentWayPointID].position - transform.position);
        transform.rotation = Quaternion.Slerp(transform.rotation, rotation, Time.deltaTime * rotationSpeed);

        //distance calculation before going to next node
        if(distance < reachDistance)
        {
            CurrentWayPointID++;
        }

        //looping back to first node
        if(CurrentWayPointID >= followPath.path_objs.Count)
        {
            CurrentWayPointID = 0;
        }
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
