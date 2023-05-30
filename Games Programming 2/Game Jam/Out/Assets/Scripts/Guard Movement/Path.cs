using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Path : MonoBehaviour
{
    private Color pathColor = Color.white;
    public List<Transform> path_objs = new List<Transform>();
    Transform[] path;

    private void OnDrawGizmos()
    {
        // initializing gizmo path
        Gizmos.color = pathColor;
        path = GetComponentsInChildren<Transform>();
        path_objs.Clear();

        //starting from the first child then to every other child
        foreach(Transform path_obj in path)
        {
            if(path_obj != this.transform)
            {
                path_objs.Add(path_obj);
            }
        }

        for(int node = 0; node < path_objs.Count; node++)
        {
            Vector3 position = path_objs[node].position;
            if(node > 0) // node that isn't the first
            {
                Vector3 previous = path_objs[node - 1].position;
                Gizmos.DrawLine(previous, position); // the line
                Gizmos.DrawWireSphere(position, 0.5f); // the dot
            }
        }   
    }
}
