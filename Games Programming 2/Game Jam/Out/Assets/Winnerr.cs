using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Winnerr : MonoBehaviour
{

    public GameController gameController;
    public GameController newCheckPoint;
    Vector3 p = new Vector3(581, 0, -442);
    // Start is called before the first frame update
    void Start()
    {

    }

    // Update is called once per frame
    void Update()
    {

    }
    private void OnTriggerEnter(Collider coll)
    {
        Debug.Log("Hit me");
        if (coll.tag == "Player")

        {
            gameController.setCheckPointLocation(p);
            gameController.Player.gameObject.SetActive(false);
            gameController.toCheckPoint();

        }
    }
}
