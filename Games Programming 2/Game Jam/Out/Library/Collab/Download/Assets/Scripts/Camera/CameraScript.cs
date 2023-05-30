using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraScript : MonoBehaviour
{
    public Transform target;
    public Transform pivot; 

    public float rotationSpeed = 3.0f;

    private float yaw = 0.0f;
    private float pitch = 0.0f;


    // Use this for initialization
    void Start()
    {
        transform.SetPositionAndRotation(Vector3.zero, new Quaternion(0, 0, 0, 0));

        pivot.transform.position = target.transform.position;
        pivot.transform.parent = target.transform;
    }

    // Update is called once per frame
    void Update()
    {
        // Maintains camera position with the Player
        transform.position = target.transform.position;

        // Camera Rotation
        yaw += rotationSpeed * Input.GetAxis("Mouse X");
        pitch -= rotationSpeed * Input.GetAxis("Mouse Y");
        transform.eulerAngles = new Vector3(pitch, yaw, 0f);

        float vertical = rotationSpeed * Input.GetAxis("Mouse Y");
        pivot.Rotate(vertical, 0f, 0f);

        // Player rotation
        float horizontal = rotationSpeed * Input.GetAxis("Mouse X");
        target.Rotate(0f, horizontal, 0f);


    }
}
