using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class NoteUI : MonoBehaviour
{
    [SerializeField] private Image noteImage;
    public string Note_Value = "Example";

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {

    }

    public void Set_Note_Visual(string noteValue)
    {
        switch (noteValue)
        {
            case "Example":
            case "Ex":
                noteImage.sprite = Resources.Load<Sprite>("Notes/" + "Example");
                break;

            default:
                noteImage.sprite 
                    = 
                    Resources.Load<Sprite>("Notes/" + noteValue);
                break;
        }
    }
}
