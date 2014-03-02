-- File: RayTracer.hs

-- Author: Christopher Camargo
-- CMPS 112: Winter 2014

import Vec3
import Ray
import Sphere
import Light
import Data.Maybe (fromJust)

sphere1 :: Sphere -- 1st sphere.
sphere1 = Sphere (Vec3 0.0 (-2.0) (-15.0)) 4.0 (Vec3 1.00 0.32 0.36) (Vec3 0.1 0.1 0.1) (Vec3 0.8 0.8 0.8) 100.0

sphere2 :: Sphere -- 2nd sphere.
sphere2 = Sphere (Vec3 5.0 5.0 (-15.0)) 2.0 (Vec3 0.00 0.92 0.36) (Vec3 0.1 0.1 0.1) (Vec3 0.8 0.8 0.8) 100.0

sphere3 :: Sphere -- 3rd sphere.
sphere3 = Sphere (Vec3 (-8.0) (10.0) (-30.0)) 6.0 (Vec3 0.36 0.32 1.00) (Vec3 0.1 0.1 0.1) (Vec3 0.8 0.8 0.8) 100.0

spheres :: [Sphere] -- List of spheres in the scene.
spheres = [sphere1, sphere2, sphere3]

trace :: Int -> Int -> (Float, Float, Float) -- Trace routine handles intersection and lighting.
trace x y = if s == Nothing
			then (0.0, 0.0, 0.0)
			else phong (fromJust s) (p) (normal (fromJust s) p)
			where
				s = getSphere x y spheres
				p = getIntersection (getRay x y) (getVal x y (fromJust (getSphere x y spheres)))

pixels :: [(Float, Float, Float)] -- Trace each pixel.
pixels = [(trace x y) | x <- [0..511], y <- [0..511]]

tupToString :: (Num a, Num a1, Num a2, Show a, Show a1, Show a2) => (a, a1, a2) -> [Char] -- Converts a tuple to a string.
tupToString (x, y, z) = (show (x*255)) ++ " " ++ (show (y*255)) ++ " " ++ (show (z*255)) ++ "\n"

writePPM :: String -> String -> String -- Write PPM based on width and height.
writePPM width height = "P3\n" ++ width ++ " " ++ height ++ "\n255\n" ++ concat (map tupToString (reverse pixels))

main = writeFile "out.ppm" (writePPM "512" "512")