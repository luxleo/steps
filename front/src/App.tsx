import {RouterProvider} from "react-router";
import router from "@/routers/router.tsx";
import {QueryClient, QueryClientProvider} from "@tanstack/react-query";

export default function App() {
    const queryClient = new QueryClient({
        defaultOptions: {
            queries: {
                throwOnError: true,
                retry: 0,
            }
        }
    });
    return (
        <QueryClientProvider client={queryClient}>
            <RouterProvider router={router}/>
        </QueryClientProvider>
    );
};