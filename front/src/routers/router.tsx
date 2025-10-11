import {createBrowserRouter} from "react-router";
import Index from "@/pages/LandingPage";

const router = createBrowserRouter([
    {
        path: "/",
        Component: Index
    }
])
export default router