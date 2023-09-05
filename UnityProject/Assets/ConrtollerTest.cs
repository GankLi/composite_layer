using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ConrtollerTest : MonoBehaviour
{
    public GameObject controllerGo;
    public GameObject controllerLeftGo;
    public UnityEngine.UI.Text controllerLeftVelocity;
    public UnityEngine.UI.Text controllerRightVelocity;
    //public UnityEngine.UI.Image connectionStateIndicator;
    //public UnityEngine.UI.Image connectionStateIndicatorLeft;
    public SvrController controller;
    public SvrController controllerLeft;

    void Start()
    {
        //Debug.Log("ControllerStateViz Start");
        //_ControllerStateObj = transform;
        //controller = new SvrController();
        //controllerLeft = new SvrController();
    
        controllerGo.SetActive(false);
        controllerLeftGo.SetActive(false);

        //// Sync transform with SvrManager transform
        //transform.position = SvrManager.Instance.transform.position;
        //transform.rotation = SvrManager.Instance.transform.rotation;
    }

    void LateUpdate()
    {
        {
            {
                if (controller.GetButton(SvrController.svrControllerButton.Back) && controller.GetButtonUp(SvrController.svrControllerButton.Start))
                {
                    controller.Recenter();
                }
                if (controllerLeft.GetButton(SvrController.svrControllerButton.Back) && controllerLeft.GetButtonUp(SvrController.svrControllerButton.Start))
                {
                    controllerLeft.Recenter();
                }
            }

            if (controller.ConnectionState == SvrController.svrControllerConnectionState.kConnected)
            {
                controllerGo.SetActive(true);

                {
                    Color stateColor = Color.red;

                    switch (controller.ConnectionState)
                    {
                        case SvrController.svrControllerConnectionState.kConnecting:
                            stateColor = Color.yellow;
                            break;
                        case SvrController.svrControllerConnectionState.kConnected:
                            stateColor = Color.green;
                            break;
                        default:
                            stateColor = Color.red;
                            break;
                    }
                    //connectionStateIndicator.color = stateColor;
                }                

                if (controller.PredictedPose.poseStatus != 0)
                {
                    controllerGo.transform.position = controller.PredictedPosition;
                    controllerGo.transform.rotation = controller.PredictedOrientation;   
                }
                else
                {
                    controllerGo.transform.position = controller.Position;
                    controllerGo.transform.rotation = controller.Orientation;
                    Debug.LogError("预测手柄收据没拿成功！用当前手柄pose");
                }
                
                controllerRightVelocity.text = "(" + controller.GetVelocity.x + ", " + controller.GetVelocity.y + ", " + controller.GetVelocity.z + ")";
            }

            if (controllerLeft.ConnectionState == SvrController.svrControllerConnectionState.kConnected)
            {
                controllerLeftGo.SetActive(true);

                {
                    Color stateColor = Color.red;

                    switch (controllerLeft.ConnectionState)
                    {
                        case SvrController.svrControllerConnectionState.kConnecting:
                            stateColor = Color.yellow;
                            break;
                        case SvrController.svrControllerConnectionState.kConnected:
                            stateColor = Color.green;
                            break;
                        default:
                            stateColor = Color.red;
                            break;
                    }
                    //connectionStateIndicatorLeft.color = stateColor;
                }

                if (controllerLeft.PredictedPose.poseStatus != 0)
                {
                    controllerLeftGo.transform.position = controllerLeft.PredictedPosition;
                    controllerLeftGo.transform.rotation = controllerLeft.PredictedOrientation;   
                }
                else
                {
                    controllerLeftGo.transform.position = controllerLeft.Position;
                    controllerLeftGo.transform.rotation = controllerLeft.Orientation;
                    Debug.LogError("预测手柄收据没拿成功！用当前手柄pose");
                }

                controllerLeftVelocity.text = "(" + controllerLeft.GetVelocity.x + ", " + controllerLeft.GetVelocity.y + ", " + controllerLeft.GetVelocity.z + ")";
            }
        }
    }
}
