using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Timer : MonoBehaviour
{
    public Text text;

    private float TimeElapsed = 0.0f;
    private bool trackingTIme = false;

    // Start is called before the first frame update
    void Start()
    {
       
    }

    // Update is called once per frame
    void Update()
    {
        if (trackingTIme)
        {
            TimeElapsed += Time.deltaTime;
        }

        if (text != null) text.text = TimeElapsed.ToString();
    }

    public void TimerStart() { trackingTIme = true; }
    public void TImerStop() { trackingTIme = false; }

    public float showTime() { return TimeElapsed; }
}
