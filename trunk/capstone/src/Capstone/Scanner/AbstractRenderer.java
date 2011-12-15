package Capstone.Scanner;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;

//filename: AbstractRenderer.java
public abstract class AbstractRenderer implements Renderer
{
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {
        gl.glDisable(GL10.GL_DITHER);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,
                GL10.GL_FASTEST);
        gl.glClearColor(0f, 0f, 0f, 1);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        
        
    }
    
    public void onSurfaceChanged(GL10 gl, int w, int h) {
        gl.glViewport(0, 0, w, h);
        float ratio = (float) w / h;
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        
        //Regular frustum
        gl.glFrustumf(-ratio, ratio, -1, 1, 2, 1000);
        //gl.glOrthof(-100, 100, -150, 150, 10, 300);
        //4 times bigger
        //gl.glFrustumf(-ratio * 4, ratio * 4, -1 * 4, 1 * 4, 3, 7);
    }
    
    public void onDrawFrame(GL10 gl) 
    {
        gl.glDisable(GL10.GL_DITHER);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
//        GLU.gluLookAt(gl, 0, 0, 200, 0f, 0f, 1000f, 0f, 1.0f, 0.0f);
        GLU.gluLookAt(gl, PointCloud.xDist, PointCloud.yDist, -PointCloud.oriCameraDist + PointCloud.zoomDist,
        		PointCloud.xDist, PointCloud.yDist, 0f, 0f, 1.0f, 0.0f);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        draw(gl);
    }
    protected abstract void draw(GL10 gl);
}
